from flask import Flask, jsonify, request, make_response, json
from flask_mysqldb import MySQL

from google.cloud import storage

import requests
import base64

app = Flask(__name__)
client = storage.Client()

bucket_name = "upload-img-rempasi"

app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'db_rempasi'

mysql = MySQL(app)


@app.route('/', methods=['GET'])
def index():
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM foods")
    rv = cur.fetchall()

    return make_response(jsonify({"data": rv}), 200)


@app.route('/ingredients', methods=['GET'])
def ingre():
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM ingredients WHERE id=1")
    rv = cur.fetchall()

    curs = mysql.connection.cursor()
    curs.execute("SELECT * FROM foods WHERE id=1")
    rl = curs.fetchall()

    return make_response(jsonify({"data": rv, "second": rl}))


@app.route('/recommendation', methods=['GET'])
def recommendation():
    ingredients = request.args.getlist('ingredients')
    placeholders = ','.join(['%s'] * len(ingredients))
    cur = mysql.connection.cursor()
    query = "SELECT DISTINCT id_food FROM ingredients WHERE ingredients IN ({})".format(
        placeholders)
    cur.execute(query, tuple(ingredients))
    foods_id = cur.fetchall()

    recommendation = []
    for food in foods_id:
        id = food[0]

        cur.execute(
            f"select food_name, image from foods where id = {id};")
        food_name = [row for row in cur.fetchall()]

        cur.execute(f"SELECT step FROM steps WHERE id_food = {id};")
        ste = [row[0] for row in cur.fetchall()]

        cur.execute(
            f"SELECT ingredients FROM ingredients WHERE id_food = {id};")
        ing = [row[0] for row in cur.fetchall()]

        recommendation.append({
            'id': id,
            'name': food_name[0][0],
            'image': food_name[0][1],
            'ingredients': ing,
            'steps': ste
        })

    cur.close()

    return make_response(
        jsonify({
            "status": True,
            "data": recommendation,
            "message": "Data fetch successfuly"
        })
    )


@app.route('/upload', methods=["POST"])
def upload():
    data = {}
    if request.method == "POST" and "image" in request.files:
        image = request.files['image']
        bucket = client.get_bucket(bucket_name)
        blob = bucket.blob(image.filename)
        blob.upload_from_file(image)

        img_bytes = blob.download_as_bytes()

        payload = {
            "instances": [
                {
                    "image_bytes": direct_encode(img_bytes)
                }
            ]
        }

    headers = {"Content-Type": "application/json"}
    r = requests.post(
        url="https://rempasi-d-3-4sfmyeft2q-uc.a.run.app/v1/models/rempasi/versions/1:predict", data=json.dumps(payload), headers=headers)

    data = clean_data(r.text)

    transformed = transform_data(data)

    final = label(transformed)

    return make_response(jsonify({"status": True, "data": final, "message": "Image successfully predicted"}))


def clean_data(response):

    # Parsing data JSON
    data = json.loads(response)

    # Menghapus item [0.0, 0.0, 0.0, 0.0] dari tf.identity
    data['predictions'][0]['tf.identity'] = [item for item in data
                                             ['predictions'][0]['tf.identity'] if item != [0.0, 0.0, 0.0, 0.0]]

    # Menghapus nilai 0.0 dari tf.identity_2
    pivot = data['predictions'][0]['tf.identity_2'] = [
        item for item in data['predictions'][0]['tf.identity_2'] if item != 0.0]
    # menghitung jumlah object yang berhasil terdeteksi dari keyakinan yang bukan 0.0
    count_object_detected = len(pivot)

    data['predictions'][0]['tf.identity_1'] = data['predictions'][0]['tf.identity_1'][:count_object_detected]

    return data['predictions'][0]


def transform_data(object):
    new_data = {
        "predictions": []
    }

    for i in range(len(object['tf.identity_2'])):
        prediction = {
            "label": round(object['tf.identity_1'][i]),
            "confidence": object['tf.identity_2'][i],
            "annotated_coordinate": object['tf.identity'][i]
        }
        new_data["predictions"].append(prediction)

    return new_data


def label(object):
    label_mapping = {
        0: 'Carrot',
        1: 'Potato',
        2: 'Tomato',
        3: 'Banana',
        4: 'Cheese',
        5: 'Orange',
        6: 'Brocoli',
        7: 'Egg',
        8: 'Apple',
        9: 'Pumpkin',
        10: 'Shrimp',
        11: 'Mango',
        12: 'Pineapple',
        13: 'Watermelon'
    }

    for i in object['predictions']:
        i['label'] = label_mapping.get(i['label'], 'unknown')

    return object


def direct_encode(path):
    encoded_string = base64.urlsafe_b64encode(path).decode()

    return encoded_string

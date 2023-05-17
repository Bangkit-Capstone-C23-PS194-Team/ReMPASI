const http = require("http");

const server = http
  .createServer((req, res) => {
    res.setHeader("Content-Type", "text/html");
    res.statusCode = 200;
    res.end("Howdy");
  })
  .listen(3000);

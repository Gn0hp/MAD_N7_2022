const path = require('path');
const express = require('express');
const Route = require('./routes/index')
const app = express();
const port = 3124

app.use(express.json());
app.use(express.urlencoded({extended: true}));

const db = require('./db/mongodb/dbseed')
db.connect()
app.listen(port, () => {
    console.log(`server started listenning at http://localhost:${port}`)
});

Route(app)
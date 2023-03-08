import { DBService } from "./db/mongodb/dbseed";
import { route } from "./routes";

const express = require('express');
const app = express();
const port = 3124

app.use(express.json());
app.use(express.urlencoded({extended: true}));

const dbsrv = new DBService()
dbsrv.connect()
app.listen(port, () => {
    console.log(`server started listenning at http://localhost:${port}`)
});

route(app)
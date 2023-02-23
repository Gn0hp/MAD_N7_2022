const mongoose = require('mongoose');
import * as dotenv from 'dotenv';
dotenv.config();

const connectionString = 'mongodb+srv://'

function GetDriverSource() {
    let user = process.env.DB_USER;
    let password = process.env.DB_PASSWORD;
    let host = process.env.DB_HOST;
    let retryWrite = process.env.DB_RETRYWRITE;
    let w = process.env.DB_W;

    return `${connectionString}${user}:${password}@${host}?retryWrites=${retryWrite}&w=${w}`
}
(async function(){
    let url = GetDriverSource();
    try {
        await mongoose.connect(
        url,
        {
            useNewUrlParser: true,
            useUnifiedTopology: true,
        }
    ).then(() => console.log('DB connected!'))
    }
    catch(err) {
        console.log(err)
    }
})()
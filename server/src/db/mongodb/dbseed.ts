const mongoose = require('mongoose');
const MONGOOSE_ADMIN  = mongoose.mongo.Admin;
import * as dotenv from 'dotenv';
dotenv.config();

const connectionString = 'mongodb+srv://'

function GetDriverSource() {
    let user = process.env.DB_USER_01;
    let password = process.env.DB_PASSWORD_01;
    let host = process.env.DB_HOST;
    let retryWrite = process.env.DB_RETRYWRITE;
    let w = process.env.DB_W;

    return `${connectionString}${user}:${password}@${host}?retryWrites=${retryWrite}&w=${w}`
}
export async function connect(){
    let url = GetDriverSource();
    try {
        console.log('connect string: ',url)
        mongoose.set('strictQuery',false)
        await mongoose.connect(
            url,
            {
                useNewUrlParser: true,
                useUnifiedTopology: true,
            }
        ).then(() => console.log('Connect to mongodb successfully'))

        // let connection = mongoose.connection
        // connection.once('open', async () => {
        //     const colletion = connection.db.collection("OpenAI_ChatHistories")
        //     await colletion.find({}).then((data: any) => {
        //         console.log(data)
        //     })
        // })
    }
    catch(err) {
        console.log(err)
    }
}
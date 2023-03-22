import mongoose from "mongoose"
import { ChatCompletion } from "../models/entities/ChatCompletion"
import { Message } from "../models/entities/Message"

export class QueryDbController {
    home(req, res){
        
    }
    async messageListByUserID(req,res) {
        let messageList = await Message.findByUserId(req?.body?.user_id)
        
        res.status(200).send(messageList)
    }
}

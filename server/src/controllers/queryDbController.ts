import mongoose from "mongoose"
import { ChatCompletion } from "../models/entities/ChatCompletion"
import { Message } from "../models/entities/Message"

export class QueryDbController {
    home(req, res){
        
    }
    async messageListByUserID(req,res) {
        let userId = req?.body?.user_id
        console.log(userId)
        let messageList = await Message.findByUserId(userId)
        console.log(messageList)
        res.status(200).send(messageList)
    }
    async chatCompletionListByUserID(req,res) {
        let chatCompletionList = await ChatCompletion.findByUserId(req?.body?.user_id)
        res.status(200).send(chatCompletionList)
    }
}

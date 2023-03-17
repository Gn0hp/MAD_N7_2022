import { Message } from "../models/entities/Message"
import ChatGPTService from "../models/openAi/chatgpt"

export class GPTController {

    home(req, res) {
        res.status(200).send('Access to gpt route!')
    }

    async chat(req,res) {
        // let chatgpt = new ChatGPTService(process.env.CHATGPT_ROLE_CHAT_BOT as string,process.env.OPENAI_APIKEY as string)
        // let response = await chatgpt.generateCompletion(prompt)
        // console.log(response)
        // res.status(200).send(response)
        // console.log(Message)
    }
    async postChat(req,res) {   
        let bodyParser = req.body
        let prompt = bodyParser?.prompt
        let userID = bodyParser?.username
        console.log('prompt: ', prompt)
        let chatgpt = new ChatGPTService(process.env.CHATGPT_ROLE_CHAT_BOT as string,process.env.OPENAI_APIKEY as string)
        let [response, chat_completion_id] = await chatgpt.generateCompletion(prompt,userID)
        console.log(chat_completion_id)
        res.status(200).send(response)
        
    }
    async continueChat(req,res){
        let bodyParser = req.body
        let prompt = bodyParser?.prompt
        let chatId = bodyParser?.chat_id
        
    }
}
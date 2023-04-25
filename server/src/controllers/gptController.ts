import { CHATGPT_ROLE_CHAT_BOT } from "../utils/Constant"
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
    /* /chat/Chat
    @param prompt: string
    @param user_id: string
    @param chat_id: string
    */
    async postChat(req,res) {   
        let bodyParser = req.body
        console.log("---------------",bodyParser)
        let prompt = bodyParser?.prompt
        let chatId = bodyParser?.chat_id
        let userID = bodyParser?.user_id
        console.log('prompt: ', prompt)
        let chatgpt = new ChatGPTService(CHATGPT_ROLE_CHAT_BOT,process.env.OPENAI_APIKEY as string)
        let response = await chatgpt.generateCompletion(prompt, userID, chatId)
        console.log(response)
        let [message, chatCompletionID] = response
        res.status(200).send(response)
        
    }
    /* /chat/continueChat 
    @param prompt: string
    @param user_id: string
    @param chat_id: string
    */

    async continueChat(req,res){
        let bodyParser = req.body
        let prompt = bodyParser?.prompt
        let chatId = bodyParser?.chat_id
        let userId = bodyParser?.user_id
        let messageList = await Message.findByUserId(userId)
        let contentObjectJson: {}[] = []
        if(messageList){
            messageList.forEach((message) => {
                let currMess = {
                    role: message.role,
                    content: message.content
                }
                contentObjectJson = [
                    ...contentObjectJson,
                    currMess
                ]
            })
        }
        let chatgpt = new ChatGPTService(CHATGPT_ROLE_CHAT_BOT,process.env.OPENAI_APIKEY as string)
        let response = await chatgpt.continueChatCompletion(chatId, userId, prompt, contentObjectJson)
        let [message, chatCompletionID] = response
        console.log(response)
        
        res.status(200).send(response)
    }
}
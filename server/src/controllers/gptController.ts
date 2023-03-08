import ChatGPTService from "../models/openAi/chatgpt"

export class GPTController {

    home(req, res) {
        res.status(200).send('Access to gpt route!')
    }

    async chat(req,res) {
        let chatgpt = new ChatGPTService("",process.env.OPENAI_APIKEY)
        let response = await chatgpt.generateCompletion("hey")
        console.log(response)
        res.status(200).send(response)
    }
}
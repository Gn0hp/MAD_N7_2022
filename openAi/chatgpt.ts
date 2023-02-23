import {Configuration, OpenAIApi} from "openai";
//using dotenv to call the api key from .env file
import * as dotenv from 'dotenv';
import GPTInterface from "./GPTInterface";
dotenv.config();

export default class ChatGPTService implements GPTInterface{ 

    _openAiConfig = new Configuration({
        apiKey: process.env.OPENAI_APIKEY,
    })
    
    
    _rolePlayIntroduction

    constructor(description: string) {
        this._rolePlayIntroduction = description;
    }

    generateCompletion = async (prompt) => {
        let fullPrompt = this._rolePlayIntroduction +'\n\n';
        let ChatGPT = new OpenAIApi(this._openAiConfig);

        fullPrompt += `User: ${prompt}\n`;
        fullPrompt += `AI: `;

        const completions = await ChatGPT.createCompletion({
            model: 'text-davinci=003',
            prompt: fullPrompt,
            temperature: 0.5,
            max_tokens: 2000,
            top_p: 1,
            frequency_penalty: 0,
            presence_penalty: 0,
        })
        return completions?.data?.choices[0]?.text?.replace(/^\s+|\s+$/g, "");
        
    }
}
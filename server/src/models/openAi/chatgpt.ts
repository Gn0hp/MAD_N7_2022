import {Configuration, OpenAIApi} from "openai";
//using dotenv to call the api key from .env file
import * as dotenv from 'dotenv';
import GPTInterface from "./GPTInterface";
import { CHATGPT_MODEL_STABLE } from "../../utils/Constant";
dotenv.config();

export default class ChatGPTService implements GPTInterface{ 

    _openAiConfig
    
    _rolePlayIntroduction

    constructor(description: string, apiKey: string) {
        this._rolePlayIntroduction = description;
        this._openAiConfig = new Configuration({
            apiKey: apiKey,
        })
    }

    generateCompletion = async (prompt) => {
        let fullPrompt = this._rolePlayIntroduction +'\n\n';
        let ChatGPT = new OpenAIApi(this._openAiConfig);

        fullPrompt += `User: ${prompt}\n`;
        fullPrompt += `AI: `;
        console.log('sending request to openai...')
        const completions = await ChatGPT.createChatCompletion({
            model: CHATGPT_MODEL_STABLE,
            // prompt: fullPrompt,
            messages: [{
                role: 'user',
                content: fullPrompt
            }],
            temperature: 0.5,
            max_tokens: 2000,
            top_p: 1,
            frequency_penalty: 0,
            presence_penalty: 0,
        })
        return completions?.data?.choices[0]?.message
        // ?.replace(/^\s+|\s+$/g, "");
    }
    generateChatCompletion = async ( prompt) => {
        let model = 'gpt-3.5-turbo'
        let fullPrompt = this._rolePlayIntroduction +'\n\n';
        this._continueChatCompletion(fullPrompt, prompt)
    }
    _continueChatCompletion = async (lastPrompt, prompt) => {

    }
}
import {Configuration, OpenAIApi} from "openai";
//using dotenv to call the api key from .env file
import * as dotenv from 'dotenv';
import GPTInterface from "./GPTInterface";
import { CHATGPT_MODEL_STABLE } from "../../utils/Constant";
import { Message } from "../entities/Message";
import { ChatCompletion } from "../entities/ChatCompletion";
import mongoose from "mongoose";
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
    generateCompletion = async (prompt: string, userID: string, chatCompletionId) => {
        let fullPrompt = this._rolePlayIntroduction +`${prompt}`+'\n\n';
        let ChatGPT = new OpenAIApi(this._openAiConfig);

        console.log('sending request to openai...')
        let temperature = 0.5
        let max_tokens = 2000
        let top_p = 1
        let frequency_penalty = 0
        let presence_penalty = 0
        const completions = await ChatGPT.createChatCompletion({
            model: CHATGPT_MODEL_STABLE,
            // prompt: fullPrompt,
            messages: [{
                role: 'user',
                content: fullPrompt
            }],
            temperature,
            max_tokens,
            top_p,
            frequency_penalty,
            presence_penalty,
        })
        console.log("response: ", completions.data.choices)
        let message = completions?.data?.choices[0]?.message
        if(message) {
            let chatCompletion = {
                api_object_id: completions?.data?.id,
                model: completions?.data?.model,
                temperature,
                max_tokens,
                top_p,
                frequency_penalty,
                presence_penalty,
                prompt_tokens: completions?.data?.usage?.prompt_tokens,
                completion_tokens: completions?.data?.usage?.completion_tokens,
                total_tokens: completions?.data?.usage?.total_tokens,
                assistant_description: this._rolePlayIntroduction,
            }
            console.log(chatCompletion)
            await ChatCompletion.updateByUserId(userID, chatCompletion)
            let messReq = new Message({
                role: 'user',
                content: prompt,
                chat_completion_id: new mongoose.Types.ObjectId(chatCompletionId),
            })
            let messRes = new Message({
                role: message?.role,
                content: message?.content,
                chat_completion_id: new mongoose.Types.ObjectId(chatCompletionId),
            })
            Message.insertMany([messReq, messRes], (err) => {
                if(err){
                    console.log(err)
                    return
                }
            })
            return [message, {"chat_completion": chatCompletionId}]
        }
        return [null, null]
    }
    continueChatCompletion = async(chatId: string, userID: string, messages: any, lastContent: any) => {
        let ChatGPT = new OpenAIApi(this._openAiConfig);
        let userMessage = messages

        let currentContent  = [...lastContent, {
            role: 'user',
            content: messages
        }]
        
        console.log('sending request to openai...')
        let temperature = 0.5
        let max_tokens = 2000
        let top_p = 1
        let frequency_penalty = 0
        let presence_penalty = 0
        const completions = await ChatGPT.createChatCompletion({
            model: CHATGPT_MODEL_STABLE,
            // prompt: fullPrompt,
            messages: currentContent,
            temperature,
            max_tokens,
            top_p,
            frequency_penalty,
            presence_penalty,
        })
        console.log("response: ", completions)
        let message = completions?.data?.choices[0]?.message
        if(message) {
            let messReq = new Message({
                role: 'user',
                content: userMessage,
                chat_completion_id: new mongoose.Types.ObjectId(chatId),
            })
            let messRes = new Message({
                role: message?.role,
                content: message?.content,
                chat_completion_id: new mongoose.Types.ObjectId(chatId),
            })
            Message.insertMany([messReq, messRes], (err) => {
                if(err){
                    console.log(err)
                    return
                }
            })
            return [message, {"chat_completion": chatId}]
        }
        return [null, null]
    }
}
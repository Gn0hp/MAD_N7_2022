import mongoose, { Model, Schema } from "mongoose";
import { IUser } from "./IUser";

export interface IChatCompletion extends mongoose.Document{
    api_object_id: String
    model: String,
    temperature: Number,
    max_tokens: Number,
    top_p: Number,
    frequency_penalty: Number,
    presence_penalty: Number,
    prompt_tokens: Number,
    completion_tokens: Number,
    total_tokens: Number,
    user_id: IUser['_id']
}
export interface IChatCompletionMethods{
    findByUserId();
}

export type ChatCompletionModel = Model<IChatCompletion,{}, IChatCompletionMethods>

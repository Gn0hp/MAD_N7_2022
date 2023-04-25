import mongoose, { Model, Schema } from "mongoose";
import { IChatCompletion } from "./IChatCompletion";

export interface IMessage extends mongoose.Document{
    role: String,
    content: String,
    chat_completion_id: IChatCompletion['_id']
}
export interface IMessageMethods{
    findByChatCompletionID();
}

export type MessageModel = Model<IMessage,{}, IMessageMethods>

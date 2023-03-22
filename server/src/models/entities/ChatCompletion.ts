import { Schema } from "mongoose";
import { ChatCompletionModel, IChatCompletion, IChatCompletionMethods } from "./interfaces/IChatCompletion";

const mongoose = require('mongoose');

const schema = new Schema<IChatCompletion, ChatCompletionModel, IChatCompletionMethods>({
    api_object_id: {
        type: String,
        index: true,
        unique: true,
    } ,
    model: String,
    temperature: Number,
    max_tokens: Number,
    top_p: Number,
    frequency_penalty: Number,
    presence_penalty: Number,
    prompt_tokens: Number,
    completion_tokens: Number,
    total_tokens: Number,
    user_id: {type: Schema.Types.ObjectId, ref: 'User'}
}, {
    timestamps: true
})

schema.statics.findByUserId = async function findByUserId(id) {
    let userID = new mongoose.Types.ObjectId(id)
    return await mongoose.model('ChatCompletion').find({user_id: userID}).populate('user_id')
}

export const ChatCompletion = mongoose.model('ChatCompletion', schema);
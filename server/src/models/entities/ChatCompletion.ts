import { Schema } from "mongoose";
import { ChatCompletionModel, IChatCompletion, IChatCompletionMethods } from "./interfaces/IChatCompletion";

const mongoose = require('mongoose');

const schema = new Schema<IChatCompletion, ChatCompletionModel, IChatCompletionMethods>({
    api_object_id: {
        type: String,
        index: true,
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
    return await mongoose.model('ChatCompletion').find({user_id: new mongoose.Types.ObjectId(id)})
}
schema.statics.updateByUserId = async function updateByUserId(id, data) {
    return await mongoose.model('ChatCompletion').updateOne({user_id: new mongoose.Types.ObjectId(id)}, data)
}
export const ChatCompletion = mongoose.model('ChatCompletion', schema);
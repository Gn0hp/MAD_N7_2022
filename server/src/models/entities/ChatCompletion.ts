import { Schema } from "mongoose";

const mongoose = require('mongoose');

const schema = new mongoose.Schema({
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

export const ChatCompletion = mongoose.model('ChatCompletion', schema);
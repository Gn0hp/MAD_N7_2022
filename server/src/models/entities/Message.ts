import { Schema } from "mongoose";

const mongoose = require('mongoose');

const schema = new mongoose.Schema({
    role: String,
    content: String,
    chat_completion_id: {type: Schema.Types.ObjectId, ref: 'ChatCompletion'},
}, {
    timestamps: true
})

schema.methods.findByChatCompletionId = async function findByChatCompletionId(id) {
    return await mongoose.model('Message').find({chat_completion_id: id}).sort({createdAt: 1})
}

export const Message = mongoose.model('Message', schema);

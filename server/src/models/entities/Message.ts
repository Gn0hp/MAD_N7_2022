import { Schema } from "mongoose";

const mongoose = require('mongoose');

const schema = new mongoose.Schema({
    role: String,
    content: String,
    chat_completion_id: {type: Schema.Types.ObjectId, ref: 'ChatCompletion'},
}, {
    timestamps: true
})

export const Message = mongoose.model('Message', schema);

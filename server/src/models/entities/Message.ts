import { Schema } from "mongoose";

const mongoose = require('mongoose');

const schema = new mongoose.Schema({
    role: String,
    content: String,
    chat_completion_id: {type: Schema.Types.ObjectId, ref: 'ChatCompletion'},
}, {
    timestamps: true
})

schema.statics.findByChatCompletionId = async function findByChatCompletionId(id) {
    console.log(id)
    let res = await mongoose.model('Message').find({chat_completion_id: id}).sort({createdAt: 1})
    if(!res.length) {
        return null;
    }
    return res;
}
schema.statics.findByUserId = async function findByUserId(userID){
    let chatcompletionId = await mongoose.model('ChatCompletion').find({user_id: new mongoose.Types.ObjectId(userID)})
    if(!chatcompletionId.length){
        
    }
    let res =  await mongoose.model('Message').find({chat_completion_id: chatcompletionId[0].id}).sort({createdAt: 1}) 
    if(!res.length) {
        return null;
    }
    return res;
}

export const Message = mongoose.model('Message', schema);

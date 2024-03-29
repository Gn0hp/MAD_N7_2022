import { Schema } from "mongoose";
import { IUser, IUserMethods, UserModel } from "./interfaces/IUser";

const mongoose = require('mongoose');

const schema = new Schema<IUser, UserModel, IUserMethods>({
    name: String,
    email: String,
    password: String,
    profileURL: String,
    username: {
        type: String,
        unique: true,
        index: true,
    },
    phoneNumber: String,
    }, {
    timestamps: true
    }
)

schema.statics.checkLogin = async function checkLogin(u) {
    let user = await mongoose.model('User').findOne(
        { username: u.username})
    if(user && user.password === u.password) {
        return [true, null,user]
    }
    return [false, new Error('Wrong password or invalid user'), null]
}
schema.statics.registerUser = async function registerUser(u) {
    let existUser = await mongoose.model('User').findOne({
        username: u.username
    })
    if(existUser){
        return [false, new Error('Exist username')]
    }
    u.save((err) => {
        if(err){
            console.log(err)
            return [false,err,null]
        }
    })
    return [true, null, u._id]
}

        

export const User = mongoose.model('User', schema);

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
    let existEmail = await mongoose.model('User').findOne({
        email: u.email
    })
    if(existEmail){
        return [false, new Error('Exist email')]
    }
    u.save((err) => {
        if(err){
            console.log(err)
            return [false,err]
        }
    })
    return [true, null]
}

        

export const User = mongoose.model('User', schema);

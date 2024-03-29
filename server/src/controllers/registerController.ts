import { ChatCompletion } from "../models/entities/ChatCompletion";
import mongoose from "mongoose";
import { User } from "../models/entities/User";

export class RegisterController {
    async signup(req,res) {
        
        let user = new User({
            name: req?.body?.name  ? req.body?.name : req?.body?.email,
            username: req?.body?.username,
            email: req?.body?.email,
            password: req?.body?.password,
            profileURL: req?.body?.profile_url ? req?.body?.profile_url : "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang.jpg",
            phoneNumber: req?.body?.phone_number
        })
        console.log(user)
        
        let [savingRes, savingErr, userID] = await User.registerUser(user)
        if(!savingRes) {
            res.status(200).send({"response": savingRes, "error": savingErr})
            return
        }
        let chatCompletion = new ChatCompletion({
            user_id: new mongoose.Types.ObjectId(userID),
        })
        chatCompletion.save((err) => {
            if(err){
                console.log(err)
                return
            }
        })
        res.status(200).send({"response": savingRes, "error": null})
    }
    async login(req,res){
        let user = new User({
            name: req?.body?.name,
            username: req?.body?.username,
            email: req?.body?.email,
            password: req?.body?.password,
            profileURL: req?.body?.profile_url,
        })

        let [loginRes, loginErr, userDB] = await User.checkLogin(user)
        if(!loginRes){
            res.status(200).send(null)     
            return
        }
        console.log(userDB)
        let chatComletion = await ChatCompletion.findByUserId(userDB._id)
        let chatCompletionID = chatComletion[0]?._id
        let responseObj = {
            ...userDB?._doc,
            chat_completion_id: chatCompletionID
        }
        console.log("------",responseObj)
        res.status(200).send(responseObj)
    }
}

import { User } from "../models/entities/User";

export class RegisterController {
    async signup(req,res) {
        let user = new User({
            name: req?.body?.name,
            username: req?.body?.username,
            email: req?.body?.email,
            password: req?.body?.password,
            profileURL: req?.body?.profile_url,
        })
        
        
        let [savingRes, savingErr] = await User.registerUser(user)
        if(!savingRes) {
            res.status(200).send({"response": savingRes, "error": savingErr})
            return
        }
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
        res.status(200).send(userDB)
    }
}

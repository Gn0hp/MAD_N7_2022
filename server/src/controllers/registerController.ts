import { User } from "../models/entities/User";

export class RegisterController {
    async signup(req,res) {
        let user = new User({
            name: req?.body?.name,
            email: req?.body?.email,
            password: req?.body?.password,
            avatar: req?.body?.avatar,
        })
        
        console.log('saving')
        let [savingRes, savingErr] = await User.registerUser(user)
        if(!savingRes) {
            res.status(400).send(`Save user failed: ${savingErr} `)
            return
        }
        res.status(200).send('Save user successfully')
    }
    async login(req,res){
        let user = new User({
            name: req?.body?.name,
            email: req?.body?.email,
            password: req?.body?.password,
            avatar: req?.body?.avatar,
        })
        let [loginRes, loginErr] = await User.checkLogin(user)
        if(!loginRes){
            res.status(400).send(`Login failed: ${loginErr}`)
            return
        }
        res.status(200).send('Login successfully')
    }
}

import { User } from "../models/entities/User";

export class RegisterController {
    async signup(req,res) {
        
        let user = new User({
            name: req?.body?.name  ? req.body?.name : req?.body?.email,
            username: req?.body?.username,
            email: req?.body?.email,
            password: req?.body?.password,
            profileURL: req?.body?.profile_url ? req?.body?.profile_url : "https://www.google.com/imgres?imgurl=https%3A%2F%2Fhaycafe.vn%2Fwp-content%2Fuploads%2F2022%2F02%2FAvatar-trang.jpg&imgrefurl=https%3A%2F%2Fhaycafe.vn%2Favatar-trang%2F&tbnid=EhaeWFmPvp1qXM&vet=12ahUKEwiknYfJjNn9AhVEB94KHfe1CisQMyhMegQIARB5..i&docid=eO6ytUmyu7A5WM&w=1000&h=631&q=avatar&ved=2ahUKEwiknYfJjNn9AhVEB94KHfe1CisQMyhMegQIARB5",
        })
        console.log(user)
        
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

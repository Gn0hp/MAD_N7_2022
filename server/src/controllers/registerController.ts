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
        await user.save(err => {
            if(err){
                console.log(err)
                return
            }
        })
        res.status(200).send('Save user successfully')
    }
}

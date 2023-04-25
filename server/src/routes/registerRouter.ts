import { RegisterController } from "../controllers/registerController"

const Express = require('express')
export const router = Express.Router()

const registerController = new RegisterController()

router.post('/signup', registerController.signup)
router.post('/login', registerController.login)
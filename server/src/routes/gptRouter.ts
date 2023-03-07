import { GPTController} from "../controllers/gptController"

const Express = require('express')
const gptController = new GPTController()
export const router = Express.Router()

router.get('/', gptController.home)

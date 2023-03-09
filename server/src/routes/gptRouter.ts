import { GPTController} from "../controllers/gptController"

const Express = require('express')
const gptController = new GPTController()
export const router = Express.Router()

router.post("/chat", gptController.postChat)
router.get('/chat', gptController.chat)
router.get('/', gptController.home)

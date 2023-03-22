import { QueryDbController } from "../controllers/queryDbController"


const Express = require('express')
export const router = Express.Router()

const queryDbController = new QueryDbController() 

router.post('/messageByUser', queryDbController.messageListByUserID)
router.post('/chatCompletionByUser', queryDbController.chatCompletionListByUserID)
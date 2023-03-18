import { QueryDbController } from "../controllers/queryDbController"


const Express = require('express')
export const router = Express.Router()

const queryDbController = new QueryDbController() 


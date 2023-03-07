import { HomeController } from "../controllers/homeController"

const Express = require('express')
export const router = Express.Router()

const homeController = new HomeController()

router.get('/', homeController.home)


const Express = require('express')
const router = Express.Router()

const homeController =  require('../controllers/homeController')

router.get('/', homeController.home)

module.exports = router;
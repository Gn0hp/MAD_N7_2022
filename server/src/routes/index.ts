const homeRouter  =  require('./homeRouter')
const gptRouter = require('./gptRouter')
function route(app){
    app.use('/', homeRouter)
    app.use('/chatgpt', )
}
module.exports = route;
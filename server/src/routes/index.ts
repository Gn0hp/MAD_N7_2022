import { router as GPTRouter} from "./gptRouter";
import { router as HomeRouter} from "./homeRouter";
import { router as RegisterRouter} from "./registerRouter";
import {router as QueryDbRouter} from "./queryDbRouter";

const homeRouter = HomeRouter
const gptRouter = GPTRouter
const registerRouter = RegisterRouter
const queryDbRouter = QueryDbRouter

export function route(app: any){
    app.use('/chatgpt', gptRouter)
    app.use('/register', registerRouter)
    app.use('/querydb', queryDbRouter)
    app.use('/', homeRouter)
}

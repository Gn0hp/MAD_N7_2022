import { router as GPTRouter} from "./gptRouter";
import { router as HomeRouter} from "./homeRouter";
import { router as RegisterRouter} from "./registerRouter";

const homeRouter = HomeRouter
const gptRouter = GPTRouter
const registerRouter = RegisterRouter

export function route(app: any){
    app.use('/chatgpt', gptRouter)
    app.use('/register', registerRouter)
    app.use('/', homeRouter)
}

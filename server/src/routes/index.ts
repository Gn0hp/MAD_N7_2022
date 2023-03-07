import { router as GPTRouter} from "./gptRouter";
import { router as HomeRouter} from "./homeRouter";

const homeRouter = HomeRouter
const gptRouter = GPTRouter
export function route(app){
    app.use('/', homeRouter)
    app.use('/chatgpt', gptRouter)
}

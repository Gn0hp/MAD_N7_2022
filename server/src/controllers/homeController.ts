export class HomeController {
    home(req,res) {
        res.status(200).send('Access to home route!')
    }
}

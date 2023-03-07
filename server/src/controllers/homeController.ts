class HomeController {
    home(res,req) {
        res.status(200).send('access to home successfully');
    }
}
module.exports = new HomeController();
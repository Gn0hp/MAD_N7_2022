export default interface GPTInterface {
    _rolePlayIntroduction
    
    generateCompletion(prompt: string, userID: string);
    generateChatCompletion(prompt: string)
}
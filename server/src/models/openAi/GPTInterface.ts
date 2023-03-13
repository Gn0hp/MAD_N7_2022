export default interface GPTInterface {
    _rolePlayIntroduction
    
    generateCompletion(prompt: string);
    generateChatCompletion(prompt: string)
}
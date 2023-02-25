export default interface GPTInterface {
    _rolePlayIntroduction
    
    generateCompletion(prompt: string);
}
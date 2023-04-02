export default interface GPTInterface {
    _rolePlayIntroduction
    
    generateCompletion(prompt: string, userID: string, chatCompletionId: any);
    continueChatCompletion(chatId: string, userID: string, prompt: any, lastContent: any)
}
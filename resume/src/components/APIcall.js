import {OpenAI} from "openai"
import {useState} from 'react'

const API_KEY = "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"
const openai = new OpenAI({
	apiKey: API_KEY,
    dangerouslyAllowBrowser:true,
});

export default async function APIcall (input) {
	const prompt = 
    `what are the most sought after technical skills related to 
    ${input} to get jobs, answer in only three words.
    If doesnot make sense, respond with only NO.`
    console.log(prompt)
    try {
        const response = await openai.chat.completions.create({
            model: "gpt-3.5-turbo",
            messages: [{"role": "user", "content": prompt}],
        });

        const data = response.choices[0]?.message?.content;
        if (data == "NO") {
            return 'Not available now.'
        }
        else {
        return data}

    } catch (error) {
      //console.error('Error calling ChatGPT API:', error);
      return 'Error processing request';
    }
};
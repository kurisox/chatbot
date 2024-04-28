import fs from 'fs';
import fetch from 'node-fetch';
import promptSync from 'prompt-sync';
import { dirname } from 'path';
const prompt = promptSync();


// get model name download url
const getDetails = () => {
    const modelName = prompt("Please enter the name of the model: ");
    let fileType = prompt('Please enter the file type: ');
    const url = prompt('Please enter the URL of the model: ');
    if (fileType.indexOf('.')) {
        fileType = "." + fileType;
    }
    return {modelName, fileType, url};
}

// create a directory for the file if it's not exists
const createDir = (modelName) => {
    const __filename = new URL(import.meta.url).pathname;
    const __dirname = dirname(__filename);
    try {
        const dirPath = `${__dirname}/${modelName}`;
        if (!fs.existsSync(dirPath)) {
            fs.mkdirSync(dirPath);
            console.log(`Directory ${modelName} successfully created`);
        } else {
            console.log(`Directory ${modelName} already exists`);
        }
    } catch (error) {
        console.log(error);
    }
}

const downloadContent = async (fileDetails) => {
    const response = await fetch(fileDetails.url);
    console.log(response);
    const buffer = await response.arrayBuffer();
    fs.writeFileSync(fileDetails.modelName + fileDetails.fileType, Buffer.from(buffer));
}

const fileDetails = getDetails();
createDir(fileDetails.modelName);
downloadContent(fileDetails);


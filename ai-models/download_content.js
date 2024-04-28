import fs from 'fs';
import fetch from 'node-fetch';
import promptSync from 'prompt-sync';
import { dirname } from 'path';
const prompt = promptSync();


// get model name download url
const getDetails = () => {
    const fileName = prompt("Please enter the name of the content: ");
    let fileType = prompt('Please enter the file type: ');
    const url = prompt('Please enter the URL of the content: ');
    if (fileType.indexOf('.')) {
        fileType = "." + fileType;
    }
    return {fileName, fileType, url};
}

// create a directory for the file if it's not exists
const createDir = (fileName) => {
    const __filename = new URL(import.meta.url).pathname;
    const __dirname = dirname(__filename);
    const dirPath = `${__dirname}/${fileName}`;
    try {
        if (!fs.existsSync(dirPath)) {
            fs.mkdirSync(dirPath);
            console.log(`Directory ${fileName} successfully created`);
        } else {
            console.log(`Directory ${fileName} already exists`);
        }
    } catch (error) {
        console.log(error);
    }
    return dirPath + "/";
}

const downloadContent = async (fileDetails, fileDestination) => {
    const response = await fetch(fileDetails.url);
    const buffer = await response.arrayBuffer();
    console.log(fileDestination);
    const filename = fileDestination + fileDetails.fileName + fileDetails.fileType;
    fs.writeFileSync(filename, Buffer.from(buffer));
}

const fileDetails = getDetails();
const fileDestination = createDir(fileDetails.fileName);
downloadContent(fileDetails, fileDestination);


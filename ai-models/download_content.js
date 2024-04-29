import promptSync from 'prompt-sync';
import { dirname } from 'path';
import axios from 'axios';
import ProgressBar from './utils/ProgressBar.js';
import fs from 'fs';
const prompt = promptSync();


// get model name download url
const getDetails = () => {
    const fileName = prompt("Please enter the name of the content: ");
    let fileType = prompt('Please enter the file type: ');
    const url = prompt('Please enter the URL of the content: ');
    if (fileType.indexOf('.')) {
        fileType = "." + fileType;
    }
    return { fileName, fileType, url };
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

//gets the details with axios and downloads the file
async function downloadContent(fileDetails, fileDestination) {
    const response = await axios({
        method: 'get',
        url: fileDetails.url,
        responseType: 'stream',
        maxRedirects: 5,
    });
    const progress = new ProgressBar(response);
    const writer = fs.createWriteStream(fileDestination + fileDetails.fileName + fileDetails.fileType);
    response.data.pipe(writer);
    response.data.on('data', (data) => {
        progress.printProgress(data.length);
    })
    response.data.on('end', () => {
        progress.printResult(fileDetails);
    })
}

const fileDetails = getDetails();
const fileDestination = createDir(fileDetails.fileName);
downloadContent(fileDetails, fileDestination);


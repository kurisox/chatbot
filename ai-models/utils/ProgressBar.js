import bytes from "bytes";

export default class ProgressBar {

    constructor(response) {
        this.totalSize = Number(response.headers['content-length']);
        this.downloaded = 0;
        this.downloadedBuffer = 0;
        this.dlStartTime;
        this.dlNow;
        this.dlStart = false; 
    }

    printProgress(downloaded) {
        this.#startTimer();
        this.downloaded += downloaded;
        if(this.downloadedBuffer + 10000000 < this.downloaded) {
            this.downloadedBuffer = this.downloaded;
            this.dlNow = (Date.now() - this.dlStartTime) / 1000;
            console.log(
                this.totalSize ? "Dowloaded:" + bytes(this.downloaded) + " of " + bytes(this.totalSize) : "Dowloaded: " + bytes(this.downloaded),
                this.dlNow + "s"
            );
        }
    }

    #startTimer(){
        if(!this.dlStart) {
            this.dlStart = true;
            this.dlStartTime = Date.now();
        }
    }

    printResult(fileDetails){
        console.log(`${fileDetails.fileName}${fileDetails.fileType} downloaded successfully in ${this.dlNow} seconds`);
    }
}

document.addEventListener('DOMContentLoaded', () => {
  const fileName = localStorage.getItem('generatedFileName');
  const fileURL = localStorage.getItem('generatedMusic');

  if (fileURL && fileName) {
    const downloadBtn = document.getElementById('downloadBtn');
    const successMessage = document.getElementById('successMessage');
    const errorMessage = document.getElementById('errorMessage');
    const homeButton = document.getElementById('homeButton');
    const playButton = document.getElementById('playBtn');

    // Hide any irrelevant notifications
    successMessage.style.display = 'none';
    errorMessage.style.display = 'none';

    // Download button functionality
    downloadBtn.addEventListener('click', () => {
      fetch(`http://10.10.36.63:8000/results/${fileName}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('File not found');
          }
          return response.blob(); // Return the file data
        })
        .then(blob => {
          // Create a download link
          const downloadUrl = URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = downloadUrl;
          a.download = fileName;
          document.body.appendChild(a);
          a.click();
          document.body.removeChild(a);

          // Show success message
          successMessage.style.display = 'block';
          homeButton.style.display = 'block';

          // Redirect to the home page after 3 seconds
          setTimeout(() => {
            window.location.href = 'home.html';
          }, 3000);
        })
        .catch(error => {
          // Show error message if downloading fails
          errorMessage.style.display = 'block';
          console.error('Error downloading file:', error.message);
        });
    });

    // Create piano sampler
    const piano = new Tone.Sampler({
      urls: {
        A0: "A0.mp3",
        C1: "C1.mp3",
        "D#1": "Ds1.mp3",
        "F#1": "Fs1.mp3",
        A1: "A1.mp3",
        C2: "C2.mp3",
        "D#2": "Ds2.mp3",
        "F#2": "Fs2.mp3",
        A2: "A2.mp3",
        C3: "C3.mp3",
        "D#3": "Ds3.mp3",
        "F#3": "Fs3.mp3",
        A3: "A3.mp3",
        C4: "C4.mp3",
        "D#4": "Ds4.mp3",
        "F#4": "Fs4.mp3",
        A4: "A4.mp3",
        C5: "C5.mp3",
        "D#5": "Ds5.mp3",
        "F#5": "Fs5.mp3",
        A5: "A5.mp3",
        C6: "C6.mp3",
        "D#6": "Ds6.mp3",
        "F#6": "Fs6.mp3",
        A6: "A6.mp3",
        C7: "C7.mp3",
        "D#7": "Ds7.mp3",
        "F#7": "Fs7.mp3",
        A7: "A7.mp3",
        C8: "C8.mp3",
      },
      release: 1,
      baseUrl: "https://tonejs.github.io/audio/salamander/",
    }).toDestination();

    // Play MIDI file from server
    playButton.addEventListener('click', () => {
      fetch(`http://10.10.36.63:8000/results/${fileName}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('File not found');
          }
          return response.arrayBuffer(); // Return the file as ArrayBuffer
        })
        .then(arrayBuffer => {
          const midi = new Midi(arrayBuffer);  // Parse the MIDI file

          // Start Tone.js Transport
          Tone.Transport.start();

          // Schedule notes to be played
          midi.tracks.forEach(track => {
            track.notes.forEach(note => {
              Tone.Transport.schedule(time => {
                piano.triggerAttackRelease(note.name, note.duration, time);  // Trigger note
              }, note.time);
            });
          });

          console.log("Playing MIDI file...");
        })
        .catch(error => {
          // Show error message if playing fails
          errorMessage.style.display = 'block';
          console.error('Error playing file:', error.message);
        });
    });
  } else {
    // If no file is found, show an error message
    document.getElementById('errorMessage').style.display = 'block';
    document.getElementById('downloadBtn').style.display = 'none';
  }
});

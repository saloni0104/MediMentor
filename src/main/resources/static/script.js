// Summary content for each report
const summaries = {
    1: [
		"Your cholesterol results show a mixed picture. HDL (“good” cholesterol) is a bit low at 30 (higher is better for heart protection).",
		"LDL (“bad” cholesterol) is 114, which is acceptable but should ideally be lower since your triglycerides (a type of fat in the blood) are high at 311. VLDL (another “bad fat” linked to triglycerides) is also elevated at 62. The cholesterol ratios (LDL/HDL = 3.8, Total/HDL = 5.8) suggest more risk than ideal, even though your total cholesterol (173) is within the normal limit.",
		"In short: your overall cholesterol isn’t too high, but the low good cholesterol and high triglycerides raise heart risk. Lifestyle changes like healthier diet, exercise, weight control, and limiting sugar/alcohol can help improve these numbers.",
    ],
    2: [
		"Your Widal test results show S. typhi (O and H antigens) at 1:40, which is a low level and usually not significant. This means there is no strong evidence of typhoid infection.",
		"For S. paratyphi (AH and BH antigens), there was no reaction, which also points away from infection.",
		"In short: these results do not suggest active typhoid or paratyphoid fever on their own. Doctors usually confirm with symptoms and other tests before diagnosis.",
    ],
    3: [
        "Your thyroid results are normal. T3 (Triiodothyronine) and T4 (Thyroxine) are hormones made by your thyroid that control energy and metabolism, and both are at healthy levels.",
		"TSH (Thyroid Stimulating Hormone) comes from the brain and signals the thyroid to make these hormones; your level is also normal. This shows your thyroid is working well, with no signs of imbalance.",
    ]
};

// PDF mapping (use real PDFs or placeholder ones)
const pdfs = {
    1: '1557381_24433399.pdf',
    2: '1681246_2547887-4.pdf',
    3: 'newone-2.pdf'
};

// Navbar content switching
document.querySelectorAll('#navItems .nav-link').forEach(link => {
    link.addEventListener('click', function (e) {
        e.preventDefault();

        // Toggle active class
        document.querySelectorAll('#navItems .nav-link').forEach(l => l.classList.remove('active'));
        this.classList.add('active');

        const section = this.dataset.section;
        const content = document.getElementById('rightContent');

        content.innerHTML = ''; // Clear previous

        if (section === 'records') {
            content.innerHTML = `<iframe
  src="https://lovable.dev/projects/17562810-af47-4f1f-91aa-666ebaea54c8"
  width="100%"
  height="600"
  style="border: none;"
  allowfullscreen
></iframe>`;
        } else if (section === 'appointments') {
            content.innerHTML = `<img src="Screenshot 2025-08-23 at 4.13.15 PM.png" width="600" height="600">
            <div><button style="background-color: #4CAF50; color: white; padding: 5px ; border: none; border-radius: 4px; cursor: pointer;" type="button" onclick="alert('Appointment scheduled!')">
            Schedule an appointment </button>
        </div>`;
        } else {
            content.innerHTML = `<img src="Screenshot 2025-08-23 at 4.17.27 PM.png" width="600" height="700"/>`;
        }
    });
});

// Report tab switching
document.querySelectorAll('#reportTabs .nav-link').forEach(tab => {
    tab.addEventListener('click', function (e) {
        e.preventDefault();

        // Switch active tab
        document.querySelectorAll('#reportTabs .nav-link').forEach(t => t.classList.remove('active'));
        this.classList.add('active');

        const reportId = this.dataset.report;

        // Update PDF viewer
        const pdfViewer = document.getElementById('pdfEmbed');
        pdfViewer.src = pdfs[reportId];

        // Update summary
        const summaryList = document.getElementById('summaryList');
        summaryList.innerHTML = '';
        summaries[reportId].forEach(item => {
            const li = document.createElement('li');
            li.textContent = item;
            summaryList.appendChild(li);
        });
    });
});

// Load default summary
window.addEventListener('DOMContentLoaded', () => {
    const summaryList = document.getElementById('summaryList');
    summaries[1].forEach(item => {
        const li = document.createElement('li');
        li.textContent = item;
        summaryList.appendChild(li);
    });
});




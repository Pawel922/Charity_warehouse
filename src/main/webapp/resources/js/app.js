document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  class Donation {
      constructor() {
          this.categories = [];
          this.institution = "";
          this.quantity = "";
          this.street = "";
          this.city = "";
          this.zipCode = "";
          this.phoneNumber = "";
          this.pickUpTime = "";
          this.pickUpDate = "";
          this.pickUpComment = "";
      }
  }
  
  function properGrammarForm(quantity) {
      if (quantity === 1) {
          return ' worek: '
      } else if (quantity > 1 && quantity < 5) {
          return ' worki: '
      } else {
          return ' worków: '
      }
  }

  function setAllValuesInSummary(donation) {
      document.querySelectorAll(".summary--text")[0].innerHTML =
          donation.quantity +
          properGrammarForm(donation.quantity) +
          donation.categories.toString();

      document.querySelectorAll(".summary--text")[1].innerHTML =
          'Dla ' +
          donation.institution.toString();

      document.querySelectorAll(".summary div div ul li")[0].innerHTML = donation.street;
      document.querySelectorAll(".summary div div ul li")[1].innerHTML = donation.city;
      document.querySelectorAll(".summary div div ul li")[2].innerHTML = donation.zipCode;
      document.querySelectorAll(".summary div div ul li")[3].innerHTML = donation.phoneNumber;
      document.querySelectorAll(".summary div div ul li")[4].innerHTML = donation.pickUpDate;
      document.querySelectorAll(".summary div div ul li")[5].innerHTML = donation.pickUpTime;
      document.querySelectorAll(".summary div div ul li")[6].innerHTML = donation.pickUpComment;
  }

  function getAllInputElements(slides) {
      const resultArray = [];
      slides.forEach(slide => {
          let results = slide.querySelectorAll(".form-group input");
          if (results.length !== 0) {
              results.forEach(item => {
                  resultArray.push(item);
              })
          }
      });
      return resultArray;
  }

  function clearWarnings() {
      document.querySelectorAll("p.warning").forEach(el => {
          el.innerHTML = "";
      });
  }

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          clearWarnings();
          let inputsToCheck;
          let atLeastOneIsSelected = false;
          if (this.currentStep == 1) {
              inputsToCheck = document.querySelectorAll("div[data-step='1'] input");
              inputsToCheck.forEach(input => {
                  if (input.checked == true) {
                      atLeastOneIsSelected = true;
                  }
              });
              atLeastOneIsSelected ? this.currentStep++ : document.querySelector("div[data-step='1'] p.warning").innerHTML = "Nie zaznaczyłes niczego";
          } else if (this.currentStep == 2) {
              inputsToCheck = document.querySelector("div[data-step='2'] input");
              const regex = /^[1-9][0-9]*/;
              regex.test(inputsToCheck.value) ? this.currentStep++ :  document.querySelector("div[data-step='2'] p.warning").innerHTML = "Wskaż ilość"
          } else if (this.currentStep == 3) {
              inputsToCheck = document.querySelectorAll("div[data-step='3'] input");
              inputsToCheck.forEach(input => {
                  if (input.checked == true) {
                      atLeastOneIsSelected = true;
                  }
              });
              atLeastOneIsSelected ? this.currentStep++ : document.querySelector("div[data-step='3'] p.warning").innerHTML = "Nie wybrałeś instytucji";
          } else if (this.currentStep == 4) {
              let allValuesCorrect = true;
              const regexToCheckEmpty = /^\s*$/;
              inputsToCheck = document.querySelectorAll("div[data-step='4'] input");
              inputsToCheck.forEach(input => {
                  if (input.name == 'zipCode') {
                      const regex = /^[0-9]{2}-[0-9]{3}$/;
                      if (regexToCheckEmpty.test(input.value)) {
                          allValuesCorrect = false;
                          document.querySelector("input[name='zipCode']").parentElement.nextElementSibling.innerHTML = "Nie może być puste"
                      } else if (!regex.test(input.value)) {
                          allValuesCorrect = false;
                          document.querySelector("input[name='zipCode']").parentElement.nextElementSibling.innerHTML = "Błędny kod pocztowy"
                      }
                  } else if (input.name == 'phoneNumber') {
                      const regex = /^[0-9]{9}$/;
                      if (regexToCheckEmpty.test(input.value)) {
                          allValuesCorrect = false;
                          document.querySelector("input[name='phoneNumber']").parentElement.nextElementSibling.innerHTML = "Nie może być puste"
                      } else if (!regex.test(input.value)) {
                          allValuesCorrect = false;
                          document.querySelector("input[name='phoneNumber']").parentElement.nextElementSibling.innerHTML = "Nieprawidłowy numer"
                      }
                  } else if (input.name == 'street') {
                      if (regexToCheckEmpty.test(input.value)) {
                          allValuesCorrect = false;
                          document.querySelector("input[name='street']").parentElement.nextElementSibling.innerHTML = "Nie może być puste"
                      }
                  } else if (input.name == 'city') {
                      if (regexToCheckEmpty.test(input.value)) {
                          allValuesCorrect = false;
                          document.querySelector("input[name='city']").parentElement.nextElementSibling.innerHTML = "Nie może być puste"
                      }
                  }
              });
              if(allValuesCorrect) {
                   this.currentStep++;
              }
          }
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          clearWarnings();
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {

      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;


      if (this.currentStep >= 5) {
          const donation = new Donation();
          const inputElements = getAllInputElements(this.slides);

          //find all checkboxes and radio elements and check which one are selected
          inputElements.forEach(inputElem => {
              //set particular attributes of Donation object
              if (inputElem.type == 'checkbox' && inputElem.checked == true) {
                  donation['categories'].push(inputElem.dataset.name);
              } else if (inputElem.type == 'radio' && inputElem.checked == true) {
                  donation['institution'] = inputElem.dataset.name;
              }
          });

          //get all attributes of Donation object excluding categories and institutions
          Object.getOwnPropertyNames(donation).splice(2,).forEach(property => {
              //set particular attributes of Donation object
              inputElements.forEach(inputElem => {
                  if(inputElem.name == property) {
                      donation[property] = inputElem.value;
                  }
              });
          });

          //get value from textarea
          const comment = document.querySelector("textarea").value;
          donation.pickUpComment = comment;

          setAllValuesInSummary(donation);
      }
    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});

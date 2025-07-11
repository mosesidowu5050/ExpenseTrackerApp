package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseResponse {

        private String id;
        private String title;
        private String description;
        private String category;
        private double expenseAmount;
        private String formattedAmount;
        private LocalDate createdAt;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public double getExpenseAmount() {
                return expenseAmount;
        }

        public void setExpenseAmount(double expenseAmount) {
                this.expenseAmount = expenseAmount;
        }

        public String getFormattedAmount() {
                return formattedAmount;
        }

        public void setFormattedAmount(String formattedAmount) {
                this.formattedAmount = formattedAmount;
        }

        public LocalDate getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDate createdAt) {
                this.createdAt = createdAt;
        }
}

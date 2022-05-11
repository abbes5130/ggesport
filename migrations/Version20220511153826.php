<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220511153826 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE news DROP FOREIGN KEY FK_BDE1366ED37788E2');
        $this->addSql('DROP INDEX IDX_BDE1366ED37788E2 ON news');
        $this->addSql('ALTER TABLE news DROP newcategoriee_id');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE News ADD newcategoriee_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE News ADD CONSTRAINT FK_BDE1366ED37788E2 FOREIGN KEY (newcategoriee_id) REFERENCES newcategorie (id)');
        $this->addSql('CREATE INDEX IDX_BDE1366ED37788E2 ON News (newcategoriee_id)');
    }
}
